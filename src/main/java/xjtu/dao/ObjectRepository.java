package xjtu.dao;

import xjtu.bean.Object;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ObjectRepository extends JpaRepository<Object, Integer> {

    @Query(value = "insert into object(pic_id, type) values(?1, ?2)", nativeQuery = true)
    @Modifying
    public void insert(Integer picId, String type);
}
