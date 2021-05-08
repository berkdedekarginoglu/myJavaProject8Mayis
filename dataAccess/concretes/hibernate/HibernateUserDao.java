package myHomeworkBackend.dataAccess.concretes.hibernate;

import myHomeworkBackend.core.dataAccess.hibernate.HibernateEntityRepositoryBase;
import myHomeworkBackend.core.entities.User;
import myHomeworkBackend.dataAccess.abstracts.UserDao;

public class HibernateUserDao extends HibernateEntityRepositoryBase<User> implements UserDao  {

}
