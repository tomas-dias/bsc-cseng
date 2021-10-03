#include <stdbool.h>
#include "t9types.h"

#ifndef _List_H
#define _List_H

List CreateList( List L );
List MakeEmptyList( List L );
bool IsEmpty( List L );
bool IsLast( Position P, List L );

Position FindList( ElementType X, List L );
Position FindPrevious( ElementType X, List L );

void InsertList( ElementType X, List L, Position P );
void DeleteList( ElementType X, List L );
void RemoveList( List L );

Position Header( List L );
Position First( List L );
Position Advance( Position P );
ElementType Retrieve( Position P );

void PrintList( List L );

#endif
