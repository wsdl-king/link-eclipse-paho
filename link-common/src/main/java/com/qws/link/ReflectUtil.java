package com.qws.link;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 */
public class ReflectUtil {

    public static final String GET = "get";

    public static final String SET = "set";

    public static String getString(String propertyName) {
        return GET + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
    }

    public static Method getMethod(String methodName, Class<?> classType) {
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getString("apple"));
    }

    public static String[] getNullPropertyNames(Object source) {
        return propertyNamesTemplate(source, null);
    }

    public static String[] getNotNullPropertyNames(Object source) {
        return propertyNamesTemplate(source, new PropertyNameOperation() {
            @Override
            public boolean doInProperty(PropertyDescriptor pd) {
                return !"class".equals(pd.getName());
            }
        });
    }

    private static String[] propertyNamesTemplate(Object source, PropertyNameOperation nameOperation) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
//            if ("class".equals(pd.getName())) continue;
            if (nameOperation != null && !nameOperation.doInProperty(pd)) continue;
            if (srcValue != null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static <T> T newInstance(Class<?> classType) throws IllegalAccessException, InstantiationException {
        return (T) classType.newInstance();
    }
}

