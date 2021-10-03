#include "../include/t9types.h"

#ifndef _T9lib_H
#define _T9lib_H

#define BUFFER_SIZE 255
#define BILLION 1000000000L
#define NUM_DIGITS 8

int CountWords( char const* const fileName );
void BuildKeyboard( ElementType Keyboard[] );
long int Encode( wchar_t word[], ElementType Keyboard[] );
void LoadDictionary( char const* const fileName, HashTable H, ElementType Keyboard[] );

#endif
