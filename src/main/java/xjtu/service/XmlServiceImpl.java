package xjtu.service;

import xjtu.dao.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class XmlServiceImpl implements XmlService{

    private ObjectRepository objectRepository;

    @Autowired
    public XmlServiceImpl(ObjectRepository objectRepository) {
        this.objectRepository = objectRepository;
    }

    @Transactional
    public void insert(Integer picId, String type) {
        objectRepository.insert(picId, type);
    }
}
