/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

/**
 *
 * @author dell
 */
interface Iservice<T> {
    void ajouter(T t);
    List<T>readAll();
    T readById(int id);
    void delete(T t);
    void modifier(T t);
}
