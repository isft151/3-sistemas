#ifndef PROPERTYTEST_H
#define PROPERTYTEST_H

#include <iostream>
#include "property.h"

using namespace std;

class PropertyTest
{
    public:
        PropertyTest();
        virtual ~PropertyTest();

        property<PropertyTest,int,READ_WRITE> count;

//FOR TEST:
//        property<PropertyTest,int,READ_ONLY> count;
//        property<PropertyTest,int,WRITE_ONLY> count;


    private:
        int m_nCount;
        int m_getCount();
        void m_setCount(int nCount);
};

#endif // PROPERTYTEST_H
