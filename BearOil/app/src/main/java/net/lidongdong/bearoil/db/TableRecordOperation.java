package net.lidongdong.bearoil.db;

import net.lidongdong.bearoil.entity.MoneyEntity;
import net.lidongdong.bearoil.entity.RecordEntity;

import java.util.List;

/**
 * @author lidongdong(一个帅的惊天动地的男人)
 * @version 1.0
 * @date 17/4/13
 * @explain
 * @function
 */

public interface TableRecordOperation {

    void addRecord(RecordEntity record);

    void removeRecord(int id);

    void updateRecords(RecordEntity record);

    RecordEntity queryRecord(int id);

    List<RecordEntity> queryRecords();

    List<RecordEntity> queryRecordsEachYear();

    List<RecordEntity> queryRecordsEachHalfOfYear();

    List<RecordEntity> queryRecordsThreeMonth();

    List<MoneyEntity> queryRecordsMoneyEachYear();

    List<MoneyEntity> queryRecordMoneyEachMonth();

}
