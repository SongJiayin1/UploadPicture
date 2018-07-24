package xjtu.dao;

import xjtu.bean.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PictureRepository extends JpaRepository<Picture, Integer> {

    public Picture findByFilename(String name);

    @Query(value = "insert into picture(filename, has_picture) values(?1, 1)", nativeQuery = true)
    @Modifying
    public void picInsert(String name);

    @Query(value = "insert into picture(filename, has_xml) values(?1, 1)", nativeQuery = true)
    @Modifying
    public void xmlInsert(String name);

    @Query(value = "update picture set has_picture = 1 where filename = ?1", nativeQuery = true)
    @Modifying
    public void picUpdate(String name);


    @Query(value = "update picture set has_xml = 1 where filename = ?1", nativeQuery = true)
    @Modifying
    public void xmlUpdate(String name);
}
