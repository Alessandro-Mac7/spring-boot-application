package it.si2001.webapp.springbootapplication.service;

import it.si2001.webapp.springbootapplication.model.Category;
import it.si2001.webapp.springbootapplication.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository) { this.categoryRepository = categoryRepository; }

    @Transactional
    public List<Category> getAll(){
        return categoryRepository.findAll();
    }
    @Transactional
    public Optional<Category> get(int id) { return categoryRepository.findById(id); }
    @Transactional
    public void delete(int id){
        categoryRepository.deleteById(id);
    }
    @Transactional
    public void save(Category category){
        categoryRepository.save(category);
    }
}
