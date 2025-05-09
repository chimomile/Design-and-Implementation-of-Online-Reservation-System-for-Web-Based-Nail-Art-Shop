package pojo;

import DAO.CategoryDAO;  // Updated to use CategoryDAO
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 * Category generated by hbm2java
 */
@ManagedBean
public class Category implements java.io.Serializable {

    private Integer categoryId;
    private String categoryName;
    private String description;
    private String poster;
    private List<Category> categoryList;
    private CategoryDAO categoryDAO = new CategoryDAO();  // Uses CategoryDAO

    public Category() {
    }

    public Category(String categoryName, String description, String poster) {
        this.categoryName = categoryName;
        this.description = description;
        this.poster = poster;
    }

    // Getter and Setter for categoryId
    public Integer getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    // Getter and Setter for categoryName
    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    // Getter and Setter for description
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter and Setter for poster
    public String getPoster() {
        return this.poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
    
    
    // Getter and Setter for categoryList
    public List<Category> getCategoryList() {
        return categoryList == null ? this.getAllCategories() : categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    // Method to retrieve all Category records from the database
    public List<Category> getAllCategories() {
        return categoryDAO.retrieveTblCategories();
    }

    public List<Category> getAllCategoryRecords() {
        List<Category> listCategory = categoryDAO.retrieveTblCategories();
        return listCategory;
    }

    // Method to retrieve a Category by ID
    public String getById() {
        String idCategory = categoryId.toString();
        List<Category> listCategory = categoryDAO.getById(idCategory);
        try {
            if (listCategory != null) {
                categoryName = listCategory.get(0).getCategoryName();
                description = listCategory.get(0).getDescription();
                poster = listCategory.get(0).getPoster();
                return "category";
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return "index";
    }

    // Method to save a new Category
    public String saveCategory() {
        categoryDAO.addCategory(this);
        categoryName = "";
        description = "";
        poster = "";
        return "categorydetail";
    }

    // Method to edit a Category
    public String editCategory() {
        categoryDAO.editCategory(this);
        categoryName = "";
        description = "";
        poster = "";
        return "categorydetail";
    }

    // Method to delete a Category by ID
    public String deleteCategory() {
        categoryDAO.deleteCategory(categoryId);
        categoryName = "";
        description = "";
        poster = "";
        return "categorydetail";
    }

    // Method to retrieve Category for editing by ID
    public String getById(int categoryId) {
        List<Category> categoryList = categoryDAO.showById(categoryId);
        try {
            this.categoryId = categoryList.get(0).getCategoryId();
            categoryName = categoryList.get(0).getCategoryName();
            description = categoryList.get(0).getDescription();
            poster = categoryList.get(0).getPoster();
            return "categoryedit";
        } catch (Exception e) {
            System.out.println(e);
        }
        return "index";
    }
}
