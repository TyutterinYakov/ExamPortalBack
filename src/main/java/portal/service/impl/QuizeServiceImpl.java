package portal.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portal.dao.QuizeRepository;
import portal.model.Quize;
import portal.service.QuizeService;

@Service
public class QuizeServiceImpl implements QuizeService{

	@Autowired
	private QuizeRepository quizeDao;
	
	@Override
	public Quize addQuize(Quize quize) {
		return quizeDao.save(quize);
	}

	@Override
	public Quize updateQuize(Quize quize) {
		return quizeDao.save(quize);
	}

	@Override
	public void removeQuize(Long id) {
		quizeDao.deleteById(id);
	}

	@Override
	public List<Quize> listQuize() {
		return quizeDao.findAll();
	}

	@Override
	public Quize getQuize(Long id) {
		Optional<Quize> quizeOptional = quizeDao.findById(id);
		if(quizeOptional.isPresent()) {
			return quizeOptional.get();
		}
		return null;
	}

	
	
}
