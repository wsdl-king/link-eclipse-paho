package com.qws.link;

import java.beans.PropertyDescriptor;

interface PropertyNameOperation {
    boolean doInProperty(PropertyDescriptor pd);
}