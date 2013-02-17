#ifndef SMART_PTR
#define SMART_PTR

#include <algorithm>

//
// Reference counting smart pointer.
// It is guaranteed that no resources are leaked in the face of exceptions.
//
template<class T>
class Smart_ptr
{
private:
    T *m_pT;
    int *m_pRefCount;

    typedef Smart_ptr<T> this_type;

    // make sure we dont delete a incomplete type pointer
    // throws nothing
    void checked_delete ()
    {
        typedef char type_must_be_complete[ sizeof(T)? 1: -1 ];
        (void) sizeof(type_must_be_complete);
        delete m_pT;
        
        delete m_pRefCount;
    }

    // throws nothing
    void swap (Smart_ptr<T> &rObj)
    {
        std::swap (m_pT, rObj.m_pT);
        std::swap (m_pRefCount, rObj.m_pRefCount);
    }

public:

    // throws nothing
	Smart_ptr ()
        :   m_pT (NULL),
            m_pRefCount (NULL)
    {
    }

    // we take addr of data on heap and use it
    Smart_ptr (T *pObj)
        :   m_pT (pObj),
            m_pRefCount (NULL)
    {
        try
        {
            m_pRefCount = new int;
        }
        catch (...)
        {
            checked_delete ();
            throw;
        }

        *m_pRefCount = 1;
    }

    // both objetcs share same memory on heap
    // throws nothing
    Smart_ptr (Smart_ptr<T> &rObj)
        :   m_pT((rObj.m_pT == NULL) ? NULL : rObj.m_pT), 
            m_pRefCount((rObj.m_pRefCount == NULL) ? NULL : rObj.m_pRefCount) 
    { 
        if (m_pRefCount != NULL)
            (*m_pRefCount)++;
    }


    // both objetcs share same memory on heap
    // throws nothing
    Smart_ptr (const Smart_ptr<T> &rObj)
        :   m_pT((rObj.m_pT == NULL) ? NULL : rObj.m_pT), 
            m_pRefCount((rObj.m_pRefCount == NULL) ? NULL : rObj.m_pRefCount) 
    { 
        if (m_pRefCount != NULL)
            (*m_pRefCount)++;
    }

    
    // now both objects are sharing same data on heap
    // throws nothing
    Smart_ptr& operator= (Smart_ptr<T> &rObj)
    {
        this_type(rObj).swap(*this);
        return *this;
    }

    // throws nothing
    ~Smart_ptr ()
    {
        if (!m_pRefCount)
            return;

        (*m_pRefCount)--;

        // last ref. so delete the memory
        if (*m_pRefCount == 0)
            checked_delete ();
    }


    T* operator->()
    {
        return m_pT;
    }

    T& operator*()
    {
        return *m_pT;
    }

    bool isAssigned()
    {
        return !(m_pT == NULL);
    }
};

#endif // SMART_PTR
