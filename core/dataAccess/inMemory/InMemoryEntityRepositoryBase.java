package myHomeworkBackend.core.dataAccess.inMemory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import myHomeworkBackend.core.dataAccess.EntityRepository;
import myHomeworkBackend.core.entities.Entity;

public class InMemoryEntityRepositoryBase<TEntity extends Entity> implements EntityRepository<TEntity> {

	ArrayList<TEntity> entities;
	
	public InMemoryEntityRepositoryBase() {
		entities = new ArrayList<TEntity>();
	}
	
	@Override
	public void Add(TEntity entity) {
		System.out.println("InMemory ile eklendi");
		entities.add(entity);
	}

	@Override
	public void Delete(TEntity entity) {
		System.out.println("InMemory ile silindi");
		entities.remove(entity);
	}

	@Override
	public void Update(TEntity entity) {
		System.out.println("InMemory ile güncellendi");
		entities.set(entities.indexOf(entity),entity);
	}

	@Override
	public List<TEntity> GetAll(Predicate<? super TEntity> predicate) {
		return entities.stream()
		.filter(predicate)
		.collect(Collectors.toList());
	}

	@Override
	public List<TEntity> GetAll() {
		return entities;
	}
	
	
	@Override
	public TEntity Get(Predicate<? super TEntity> predicate) {
		
		return entities.stream()
				.filter(predicate)
				.findAny()
				.get();
		}


}
