package com.mapper;

import java.sql.SQLException;

import com.entity.EasybuyOrderDetail;

public interface OrderDetailDao {
    public int saveOrderDetail(EasybuyOrderDetail detail) throws SQLException ;
}
