package DAO;

import pojo.NailartUtil;
import pojo.Category;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CategoryDAO {

    // Method to retrieve all Category records
    public List<Category> retrieveTblCategories() {
        List<Category> listCategory = new ArrayList<>();
        Transaction transaction = null;
        Session session = NailartUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Category");
            listCategory = query.list();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
        return listCategory;
    }

    // Method to retrieve a Category by ID
    public List<Category> getById(String idCategory) {
        Category category = new Category();
        List<Category> listCategories = new ArrayList<>();
        Transaction transaction = null;
        Session session = NailartUtil.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Category where categoryId = :categoryId");
            query.setString("categoryId", idCategory);
            category = (Category) query.uniqueResult();
            listCategories = query.list();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
        return listCategories;
    }

    // Method to search Categories dynamically by categoryId, categoryName, or description
    public List<Category> searchCategories(String categoryId, String categoryName, String description) {
        List<Category> listCategories = new ArrayList<>();
        Transaction transaction = null;
        Session session = NailartUtil.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();

            // Base query
            String queryString = "from Category where 1=1";

            // Dynamically build the query based on non-null parameters
            if (categoryId != null && !categoryId.isEmpty()) {
                queryString += " and categoryId = :categoryId";
            }
            if (categoryName != null && !categoryName.isEmpty()) {
                queryString += " and categoryName = :categoryName";
            }
            if (description != null && !description.isEmpty()) {
                queryString += " and description = :description";
            }

            Query query = session.createQuery(queryString);

            // Set query parameters dynamically
            if (categoryId != null && !categoryId.isEmpty()) {
                query.setString("categoryId", categoryId);
            }
            if (categoryName != null && !categoryName.isEmpty()) {
                query.setString("categoryName", categoryName);
            }
            if (description != null && !description.isEmpty()) {
                query.setString("description", description);
            }

            listCategories = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e);
        } finally {
            session.close();
        }
        return listCategories;
    }

    // Method to add a new Category
    public void addCategory(Category category) {
        Transaction transaction = null;
        Session session = NailartUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(category);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
    }

    // Method to edit an existing Category
    public void editCategory(Category category) {
        Transaction transaction = null;
        Session session = NailartUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(category);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
    }

    // Method to delete a Category by ID
    public void deleteCategory(Integer categoryId) {
        Transaction transaction = null;
        Session session = NailartUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Category category = (Category) session.load(Category.class, categoryId);
            session.delete(category);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
    }

    // Method to show a Category by ID for editing
    public List<Category> showById(int id) {
        List<Category> categoriesList = new ArrayList<>();
        Transaction transaction = null;
        Session session = NailartUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Category where categoryId=:categoryId");
            query.setInteger("categoryId", id);
            categoriesList = query.list();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return categoriesList;
    }
    


}
