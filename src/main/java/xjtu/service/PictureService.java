package xjtu.service;

import xjtu.bean.Picture;

public interface PictureService {

    public Picture findByFilename(String name);

    public void picInsert(String name);

    public void xmlInsert(String name);

    public void picUpdate(String name);

    public void xmlUpdate(String name);
}
