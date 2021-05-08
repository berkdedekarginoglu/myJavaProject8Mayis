package myHomeworkBackend.core.dataAccess;


import java.util.List;
import java.util.function.Predicate;

import myHomeworkBackend.core.entities.Entity;

public interface EntityRepository<TEntity extends Entity>  {
	void Add(TEntity entity);
	void Delete(TEntity entity);
	void Update(TEntity entity);
	List<TEntity> GetAll(Predicate<? super TEntity> predicate);
	List<TEntity> GetAll();
	TEntity Get(Predicate<? super TEntity> predicate);
}
