package portal.api.service;

import java.util.List;


import portal.api.dto.QuizeDto;


public interface QuizeService {
	//WRITE
	List<QuizeDto> getAnyQuiziesByCategoryId(Long categoryId);
	List<QuizeDto> getAnyQuizies();
	public QuizeDto getQuizeAny(Long id);
	public QuizeDto addQuize(QuizeDto quizeDto);
	public QuizeDto updateQuize(QuizeDto quize);
	public void deleteQuizeById(Long quizeId);
	
	//READ
	public List<QuizeDto> getActiveQuizies();
	public QuizeDto getActiveQuize(Long quizeId);
	public List<QuizeDto> getActiveQuiziesByCategoryId(Long categoryId);
}
