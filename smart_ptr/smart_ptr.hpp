#ifndef SMART_PTR
#define SMART_PTR

#include <string>
#include <iostream>

using namespace std;

template<class T>
class Smart_ptr
{
private:
    T *m_pT;
    int *m_pRefCount;
    std::string m_ctor;

public:
    Smart_ptr (T *pObj)
        :   m_pT (pObj),
            m_pRefCount (new int),
            m_ctor (string ("CTOR_1"))
    {
        *m_pRefCount = 1;

                                                                       
        cout << "Smart_ptr::Smart_ptr refcount " << *m_pRefCount << " " << m_ctor << endl;
    }

    Smart_ptr ()
        :   m_pT (NULL),
            m_pRefCount (new int),
            m_ctor (string ("CTOR_2"))
    {
        *m_pRefCount = 1;
                                                                       
        cout << "Smart_ptr::Smart_ptr refcount " << *m_pRefCount << " " << m_ctor << endl;
    }



    Smart_ptr (Smart_ptr<T> &rObj)
        :   m_pT(rObj.m_pT), 
            m_pRefCount(rObj.m_pRefCount), 
            m_ctor(string ("COPY_CTOR")) 
    { 
        (*m_pRefCount)++; 
        cout << "Smart_ptr::Smart_ptr(&) refcount of rObj " << *m_pRefCount << " " << m_ctor << endl;
    }
    
    Smart_ptr& operator= (Smart_ptr<T> &rObj)
    {
        (*m_pRefCount)--; 
        cout << "Smart_ptr::op= prev refcount " << *m_pRefCount << endl;
        if (*m_pRefCount == 0)
        {
            delete m_pRefCount; 
            delete m_pT; 
            cout << "Smart_ptr::op= delete instance" << endl;
        }
             
        m_pRefCount = rObj.m_pRefCount; 
        (*m_pRefCount)++; 
             
        m_pT = rObj.m_pT; 
        m_ctor = string ("ASSGN_CTOR"); 
             
        cout << "Smart_ptr::op= refcount " << *m_pRefCount << " " << m_ctor << endl; 
        return *this;
         
    }

    ~Smart_ptr ()
    {
        cout << "Smart_ptr::~Smart_ptr--- " << *m_pRefCount << endl;
        (*m_pRefCount)--;

        int tmp = *m_pRefCount;

        // last ref. so delete the memory
        if (*m_pRefCount == 0)
        {
            delete m_pT;
            delete m_pRefCount;
            cout << "Smart_ptr::~Smart_ptr delete instance" << endl;
        }

        cout << "Smart_ptr::~Smart_ptr refcount " << tmp << " " << m_ctor << endl;
    }


    int getVal()
    {
        return m_pT->getVal();
    }

    T* operator->()
    {
        return m_pT;
    }

    T& operator*()
    {
        return *m_pT;
    }
};

#endif // SMART_PTR
