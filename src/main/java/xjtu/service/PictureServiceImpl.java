package xjtu.service;

import xjtu.bean.Picture;
import xjtu.dao.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PictureServiceImpl implements PictureService {

    private PictureRepository pictureRepository;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    public Picture findByFilename(String name) {
        return pictureRepository.findByFilename(name);
    }

    @Transactional
    public void picInsert(String name) {
        pictureRepository.picInsert(name);
    }

    @Transactional
    public void xmlInsert(String name) {
        pictureRepository.xmlInsert(name);
    }

    @Transactional
    public void picUpdate(String name) {
        pictureRepository.picUpdate(name);
    }

    @Transactional
    public void xmlUpdate(String name) {
        pictureRepository.xmlUpdate(name);
    }
}
