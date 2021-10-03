#include <stdlib.h>
#include <stdbool.h>
#include <wchar.h>
#include "../include/list.h"
#include "../include/fatal.h"


List CreateList( List L )
{
    L = malloc( sizeof( struct ListNode ) );

    if( L == NULL )
        FatalError( "Out of memory!" );
    
    L->Next = NULL;

    return L;
}


List MakeEmptyList( List L )
{
    if( L == NULL )
    {
        L = malloc( sizeof( struct ListNode ) );

        if( L == NULL )
            FatalError( "Out of memory!" );
        
        L->Next = NULL;
    }
    else
        RemoveList( L );
    
    return L;
}


bool IsEmpty( List L )
{
    return L->Next == NULL; /* lista ligada c/header */
}


bool IsLast( Position P, List L )
{
    return P->Next == NULL;
}


Position FindList( ElementType X, List L )
{
    Position P;

    /* devolve NULL quando X não existe */
    P = First( L ); /* P = L->Next */

    while( P != NULL && wcscmp( P->Element->letters, X->letters ) != 0 )
        P = P->Next;
    
    return P;
}


Position FindPrevious( ElementType X, List L )
{
    Position P;

    P = Header( L );

    while( P->Next != NULL && wcscmp( P->Next->Element->letters, X->letters ) != 0)
        P = P->Next;
    
    return P;
}


void InsertList( ElementType X, List L, Position P )
{
    Position tmp;

    tmp = malloc( sizeof( struct ListNode ) );

    if( tmp == NULL )
        FatalError( "Out of memory!" );
    
    tmp->Element = X;
    tmp->Next = P->Next;
    P->Next = tmp;
}


void DeleteList( ElementType X, List L )
{
    Position P, tmp;

    P = FindPrevious( X, L );

    if( !IsLast( P, L ) )
    {
        tmp = P->Next;
        P->Next = tmp->Next;
        free( tmp );
    }
}


void RemoveList( List L )
{
    Position P, tmp;

    P = First( L );
    L->Next = NULL;

    while( P != NULL )
    {
        tmp = P->Next;
        free( P );
        P = tmp;
    }
}


Position Header( List L ) 
{
    return L;
}


Position First( List L )
{
    return L->Next;
}


Position Advance( Position P )
{
    return P->Next;
}


ElementType Retrieve( Position P ) 
{
    return P->Element;
}


void PrintList( List L )
{
    Position P = Header( L );

    P = First( L );
    while( P != NULL )
    {
        wprintf( L"%ls ", Retrieve( P )->letters );
        P = Advance( P );
    }

    wprintf( L"\n-\n" );
}
