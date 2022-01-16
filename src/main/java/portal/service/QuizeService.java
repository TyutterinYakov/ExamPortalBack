package portal.service;

import java.util.List;

import portal.model.Quize;

public interface QuizeService {
	public Quize addQuize(Quize quize);
	public Quize updateQuize(Quize quize);
	public void removeQuize(Long id);
	public List<Quize> listQuize();
	public Quize getQuize(Long id);
}
