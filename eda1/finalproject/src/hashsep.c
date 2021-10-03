#include <stdlib.h>
#include <stdbool.h>
#include <wchar.h>
#include "../include/hashsep.h"
#include "../include/list.h"
#include "../include/fatal.h"


static bool isPrime( int n )
{
    // This is checked so that we can skip
    // middle five numbers in below loop
    if (n % 2 == 0 || n % 3 == 0) return false;
        
    for (int i = 5; i * i <= n; i = i + 6)
        if (n % i == 0 || n % (i + 2) == 0)
        return false;
        
    return true;
}


/* Return next prime; assume N >= 10 */
static int NextPrime( int N )
{
    int prime = N;
    bool found = false;

    // Loop continuously until isPrime returns
    // true for a number greater than n
    while (!found)
    {
        prime++;

        if (isPrime(prime))
            found = true;
    }
    
    return prime;
}


/* Hash function for ints */
Index Hash( long int digits, int TableSize )
{
    return digits % TableSize;
}


HashTable InitializeTable( int TableSize )
{
    HashTable H;
    int i;

    /* Check TableSize */
    if( MinTableSize > TableSize )
    {
        Error( "Size too small!!!" );
        return NULL;
    }

    /* Allocate table */
    H = malloc( sizeof( struct HashTbl ) );
    if( H == NULL )
        FatalError( "Out of space!!!" );
    
    H->TableSize = NextPrime( TableSize );

    /* Allocate array of lists */
    H->TheLists = malloc( sizeof(List) * H->TableSize );
    if( H->TheLists == NULL )
        FatalError( "Out of space!!!" );
    
    /* Allocate list headers */
    for( i = 0; i < H->TableSize; i++ )
        H->TheLists[i] = CreateList(NULL);
    

    return H;
}


void DestroyTable( HashTable H )
{
    H = MakeEmpty( H );

    /* Free array of lists */
    free( H->TheLists );

    /* Free hash table */
    free( H );
}


List RetrieveList( long int code, HashTable H )
{
    Index I = Hash( code, H->TableSize );

    return H->TheLists[I];
}


Position Find( ElementType Key, HashTable H )
{
    Index I;

    I = Hash( Key->digits, H->TableSize );

    return FindList( Key, H->TheLists[I] );
}


void Insert( ElementType Key, HashTable H )
{
    Index I;

    I = Hash( Key->digits, H->TableSize );
    
    InsertList( Key, H->TheLists[I], Header( H->TheLists[I] ) );
}


HashTable Delete( ElementType X, HashTable T )
{
    Index I;

    I = Hash( X->digits, T->TableSize );
    DeleteList( X, T->TheLists[I] );

    return T;
}


HashTable MakeEmpty( HashTable T )
{
    int i;

    /* Traverse the table */
    for( i = 0; i < T->TableSize; i++ )
        MakeEmptyList( T->TheLists[i] );
    
    return T;
}


void PrintHashTable( HashTable T )
{
    int i;
    
    for( i = 0; i < T->TableSize; i++ )
    {
        wprintf( L"%ld | ", i);
        PrintList( T->TheLists[i] );
    }
}