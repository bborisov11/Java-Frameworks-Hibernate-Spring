package app.exam.service.impl;

import app.exam.domain.dto.json.ItemJSONImportDTO;
import app.exam.domain.entities.Category;
import app.exam.domain.entities.Item;
import app.exam.parser.interfaces.ModelParser;
import app.exam.repository.CategoryRepository;
import app.exam.repository.ItemsRepository;
import app.exam.service.api.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Validator;


@Service
@Transactional
public class ItemsServiceImpl implements ItemsService {
    private ItemsRepository itemsRepository;
    private CategoryRepository categoryRepository;
    private ModelParser modelParser;
    private Validator validation;
    @Autowired
    public ItemsServiceImpl(ItemsRepository itemsRepository, CategoryRepository categoryRepository, ModelParser modelParser, Validator validation) {
        this.itemsRepository = itemsRepository;
        this.categoryRepository = categoryRepository;
        this.modelParser = modelParser;

        this.validation = validation;
    }

    @Override
    public void create(ItemJSONImportDTO itemJSONImportDTO) {
        if(validation.validate(itemJSONImportDTO).size() != 0) {
            throw new IllegalArgumentException();
        }
        Item item = this.modelParser.convert(itemJSONImportDTO, Item.class);
        if(this.itemsRepository.findByName(item.getName()) != null) {
            throw new IllegalArgumentException();
        }
        Category category = this.categoryRepository.findByName(itemJSONImportDTO.getName());
        if(category == null) {
            category = new Category(itemJSONImportDTO.getCategory());
        }
        item.setCategory(category);
        category.getItems().add(item);

        this.itemsRepository.save(item);
        this.categoryRepository.save(category);
    }
}
