package com.heroku.springboot.heroku.app.service;

import com.heroku.springboot.heroku.app.domain.dto.*;
import com.heroku.springboot.heroku.app.entity.AssetEntity;
import com.heroku.springboot.heroku.app.entity.FinanceTargetCategoryEntity;
import com.heroku.springboot.heroku.app.entity.FinanceTargetDetailEntity;
import com.heroku.springboot.heroku.app.entity.TransactionEntity;
import com.heroku.springboot.heroku.app.repository.IAssetRepository;
import com.heroku.springboot.heroku.app.repository.IFinanceCategoryRepository;
import com.heroku.springboot.heroku.app.repository.IFinanceTargetDetailRepository;
import com.heroku.springboot.heroku.app.repository.ITransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FiniconService implements IFiniconService {

    @Autowired
    private IAssetRepository assetRepository;

    @Autowired
    private IFinanceCategoryRepository financeCategoryRepository;

    @Autowired
    private ITransactionRepository transactionRepository;

    @Autowired
    private IFinanceTargetDetailRepository financeTargetDetailRepository;

    @Override
    public FiniconHomepageDTO getFiniconHomepage() {
        FiniconHomepageDTO dto = new FiniconHomepageDTO();
        List<AssetEntity> assets = assetRepository.findAll();
        dto.setAssetDtos(assets.stream()
                .map(this::assetMapper)
                .collect(Collectors.toList()));
        dto.setTotalAmount(getTotalSpendingAmount());
        dto.setExpenseDetailDtos(getExpenseHomepage(transactionRepository.findAll()));

        List<FinanceTargetDetailEntity> financeTargetCategory = financeTargetDetailRepository.findAll();
        dto.setFinanceDetailDtos(financeTargetCategory.stream()
                .map(financeDetail -> {
                    FinanceDetailDto detailDto = new FinanceDetailDto();
                    detailDto.setId(financeDetail.getId());
                    detailDto.setCategoryName("Mua " + financeDetail.getFinanceTargetCategory().getName());
                    detailDto.setAmount(financeDetail.getAmount());
                    detailDto.setStatus(initStatus(financeDetail.getCreateDate(), financeDetail.isStatus()));
                    long percent = dto.getTotalAmount() * 100 / financeDetail.getAmount();
                    detailDto.setCompletePercent(percent > 100 ? String.valueOf(100L) : percent + "");
                    return detailDto;
                }).collect(Collectors.toList()));
        return dto;
    }

    private Long getTotalSpendingAmount() {
        return assetRepository.findAll().stream().mapToLong(AssetEntity::getAmount).sum();
    }

    private String initStatus(Timestamp createDate, boolean isActive) {
        String status = "Đã dừng";
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime createDateTime = createDate.toLocalDateTime();
        if (isActive) {
            status = now.getDayOfYear() == createDateTime.getDayOfYear()
                    && now.getYear() == createDateTime.getYear()
                    ? "Mới" : "Đang thực hiện";
        }

        return status;
    }

    private List<ExpenseDetailDTO> getExpenseHomepage(List<TransactionEntity> transactions) {
        List<ExpenseDetailDTO> expenseDetailDTOS = new ArrayList<>();
        LocalDate localDate = LocalDate.now();
        for (int i = 0; i < 12; i++) {
            ExpenseDetailDTO dto = new ExpenseDetailDTO();
            dto.setTitle(localDate.getMonthValue() + "");
            dto.setMonthAndYear("Tháng " + localDate.getMonthValue() + "/" + localDate.getYear());
            LocalDate finalLocalDate = localDate;
            List<TransactionEntity> tranInMonth = transactions.stream()
                    .filter(transaction -> isValidTimestamp(transaction.getCreateDate(), finalLocalDate))
                    .collect(Collectors.toList());

            dto.setTotalIncome(tranInMonth.stream()
                    .filter(TransactionEntity::isIncomeTran)
                    .mapToLong(TransactionEntity::getAmount)
                    .sum());

            dto.setTotalExpenses(tranInMonth.stream()
                    .filter(transactionEntity -> !transactionEntity.isIncomeTran())
                    .mapToLong(TransactionEntity::getAmount)
                    .sum());
            localDate = localDate.minusMonths(1);

            expenseDetailDTOS.add(dto);
        }


        return expenseDetailDTOS;
    }

    private boolean isValidTimestamp(Timestamp timestamp, LocalDate localDate) {
        LocalDateTime time = timestamp.toLocalDateTime();
        return time.getMonth() == localDate.getMonth() && time.getYear() == localDate.getYear();
    }

    private AssetDto assetMapper(AssetEntity asset) {
        AssetDto assetDto = new AssetDto();
        assetDto.setId(asset.getId());
        assetDto.setAssetName(asset.getName());
        assetDto.setAssetAmount(asset.getAmount());
        return assetDto;
    }

    @Override
    public List<FinanceCategoryDto> getFinanceCategories() {
        List<FinanceTargetCategoryEntity> financeTargetCategories = financeCategoryRepository.findAll();
        return financeTargetCategories.stream()
                .filter(FinanceTargetCategoryEntity::isStatus)
                .map(financeTargetCategory -> {
                    FinanceCategoryDto dto = new FinanceCategoryDto();
                    dto.setId(financeTargetCategory.getId());
                    dto.setCategoryName(financeTargetCategory.getName());
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public FinanceTargetCreateResponseDto addFinanceTargetDetail(FinanceTargetCreateRequestDto requestDto) {
        FinanceTargetDetailEntity entity = new FinanceTargetDetailEntity();
        entity.setAmount(requestDto.getAmount());
        entity.setNote(requestDto.getNote());
        entity.setCreateDate(new Timestamp(System.currentTimeMillis()));
        entity.setFinanceTargetCategory(financeCategoryRepository.getById(requestDto.getFinanceCategoryId()));
        entity.setStatus(true);
        entity.setNumOfMonths(requestDto.getNumOfMonths());
        financeTargetDetailRepository.save(entity);

        if (requestDto.getIncomeExtras() != null && requestDto.getIncomeExtras() > 0) {
            AssetEntity assetEntity = new AssetEntity();
            assetEntity.setName("Khác");
            assetEntity.setAmount(requestDto.getIncomeExtras());
            assetEntity.setType(2L);
            assetRepository.save(assetEntity);
        }

        FinanceTargetCreateResponseDto responseDto = new FinanceTargetCreateResponseDto();
        responseDto.setId(entity.getId());
        responseDto.setTargetAmount(entity.getAmount());
        responseDto.setTotalOutstanding(getTotalSpendingAmount());
        responseDto.setSuggestEnable(isOnSuggest(responseDto.getTotalOutstanding(), responseDto.getTargetAmount(), requestDto.getNumOfMonths()));
        return responseDto;
    }

    @Override
    public FinanceTargetCreateResponseDto getFinanceDetail(Long id) {
        FinanceTargetCreateResponseDto dto = new FinanceTargetCreateResponseDto();
        FinanceTargetDetailEntity financeTargetDetail = financeTargetDetailRepository.findById(id).get();
        dto.setId(financeTargetDetail.getId());
        dto.setTotalOutstanding(getTotalSpendingAmount());
        dto.setTargetAmount(financeTargetDetail.getAmount());
        dto.setSuggestEnable(isOnSuggest(dto.getTotalOutstanding(), dto.getTargetAmount(), financeTargetDetail.getNumOfMonths()));

        return dto;
    }

    private boolean isOnSuggest(Long totalOutstanding, Long amount, Integer numOfMonths) {
        long DEFAULT_SALARY = 25000000L;
        if (numOfMonths != null) {
            return (amount - totalOutstanding) / DEFAULT_SALARY > numOfMonths;
        }

        return totalOutstanding.doubleValue() / amount > 0.3;
    }
}
