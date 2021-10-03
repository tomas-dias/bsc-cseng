#include "t9types.h"

#ifndef _HashSep_H
#define _HashSep_H

#define MinTableSize (10)

HashTable InitializeTable( int TableSize );
void DestroyTable( HashTable H );

List RetrieveList( long int code, HashTable H );
Position Find( ElementType Key, HashTable H );
void Insert( ElementType Key, HashTable H );

HashTable Delete( ElementType X, HashTable T );
HashTable MakeEmpty( HashTable T );

void PrintHashTable( HashTable T );

#endif
