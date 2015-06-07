#include "../include/PropertyTest.h"

PropertyTest::PropertyTest()
{
    count.setContainer(this);
    count.setter(&PropertyTest::m_setCount);
    count.getter(&PropertyTest::m_getCount);
}

PropertyTest::~PropertyTest()
{
    //dtor
}

int PropertyTest::m_getCount()
{
    return m_nCount;
}

void PropertyTest::m_setCount(int nCount)
{
    m_nCount = nCount;
}
