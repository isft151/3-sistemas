#include <iostream>
#include <vector>

using namespace std;

template <typename Type, class Method> class Property
{
    private:
        Type data;
        Method *m_instance;
        Type (Method::*m_getter)(void);
        Type (Method::*m_setter)(const Type &);

    public:
        Property(Method *instance,
                        Type (Method::*getter)(void),
                        Type (Method::*setter)(const Type &) = NULL)
        {
            m_instance = instance;
            m_getter = getter;
            m_setter = setter;
        }

        Property(Method *instance,
                            Type initialValue,
                            Type (Method::*getter)(void),
                            Type (Method::*setter)(const Type &) = NULL)
        {
            m_instance = instance;
            m_getter = getter;
            m_setter = setter;
            data = initialValue;
        }

        Property()
        {
        }

        inline operator Type(){ return (m_instance->*m_getter)(); }

        inline Type operator = (const Type &value){ return (m_instance->*m_setter)(value); }

        friend Method;
};

class IntVector
{

    private:
            int m_setSize(const int &newValue)
            {
                vectorData.resize(newValue);
                return vectorData.size();
            }

            int m_getSize(void)
            {
                return vectorData.size();
            }
            std::vector<int> vectorData;

    public:
            Property<int, IntVector> length;
            IntVector():
            length(this, &IntVector::m_getSize, &IntVector::m_setSize){}
};

int main(int argc, char *argv[])
{
        IntVector* myVector = new IntVector;

        myVector->length = 32; // Sets the vector size to 32 elements.
        cout << "Vector size: " << myVector->length;//gets the vector.

        delete myVector;

        return 0;
}
