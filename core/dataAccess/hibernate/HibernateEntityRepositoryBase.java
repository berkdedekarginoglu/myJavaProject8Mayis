package myHomeworkBackend.core.dataAccess.hibernate;

import java.util.List;
import java.util.function.Predicate;

import myHomeworkBackend.core.dataAccess.EntityRepository;
import myHomeworkBackend.core.entities.Entity;

public class HibernateEntityRepositoryBase<TEntity extends Entity> implements EntityRepository<TEntity> {

	@Override
	public void Add(TEntity entity) {
		System.out.println("Hibernate ile eklendi");
		
	}

	@Override
	public void Delete(TEntity entity) {
		System.out.println("Hibernate ile silindi");
		
	}

	@Override
	public void Update(TEntity entity) {
		System.out.println("Hibernate ile güncellendi");
		
	}

	@Override
	public List<TEntity> GetAll() {
		return null;
	}

	@Override
	public TEntity Get(Predicate<? super TEntity> predicate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TEntity> GetAll(Predicate<? super TEntity> predicate) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
