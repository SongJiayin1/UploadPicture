package xjtu.dao;

import xjtu.bean.Picture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PageRespository extends PagingAndSortingRepository<Picture, Integer> {

    @Query(value = "select * from picture where has_picture = 1", nativeQuery = true)
    Page<Picture> findAll(Pageable pageable);
}
