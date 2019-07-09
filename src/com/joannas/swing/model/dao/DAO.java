package com.joannas.swing.model.dao;

import com.joannas.swing.conroller.exceptions.CantLoadDictionaryException;
import com.joannas.swing.model.Dictionary;

import java.io.File;

public interface DAO {
    Dictionary loadDictionary(File file) throws CantLoadDictionaryException;
}
