package dao;

import java.util.List;

import model.Email;

public interface MailingListDAO {

	public int save(Email email);

	public Email findByPrimaryKey(Email email);

	public List<Email> findAll();

	public void delete(Email email);

}
