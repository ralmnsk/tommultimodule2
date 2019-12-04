package com.github.ralmnsk.service.contact;

import com.github.ralmnsk.dao.contact.ContactRepository;
import com.github.ralmnsk.model.contact.Contact;
import com.github.ralmnsk.model.contact.ContactDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceRepoImpl implements ContactService {

    @Autowired
    private ContactRepository repo;
    @Autowired
    private ModelMapper mapper;


    @Override
    public void create(ContactDto contactDto) {
        Contact contact=mapper.map(contactDto,Contact.class);
        repo.save(contact);
    }

    @Override
    public ContactDto read(Long id) {
        Contact contact=repo.getOne(id);
        if (contact!=null){
            ContactDto contactDto=mapper.map(contact,ContactDto.class);
            return contactDto;
        }
        return null;
    }

    @Override
    public void update(Long id, String newMail) {
        Contact contact=repo.getOne(id);
        contact.setMail(newMail);
        repo.save(contact);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<ContactDto> findAll(int page, int size) {
        List<Contact> list=repo.findAll(PageRequest.of(page,size)).getContent();
        if ((list!=null)&&(list.size()>0)){
            List<ContactDto> listDto=list
                    .stream()
                    .map(c->mapper.map(c,ContactDto.class))
                    .collect(Collectors.toList());
            return listDto;
        }
        return null;
    }

    @Override
    public Long countAllContacts() {
        return repo.countAllContacts();
    }
}
