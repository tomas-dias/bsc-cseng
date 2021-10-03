#include <stdlib.h>
#include <stdio.h>
#include <wchar.h>
#include <ctype.h>
#include "../include/hashsep.h"
#include "../include/t9lib.h"


int CountWords( char const* const fileName )
{
    int words = 0;

    FILE* file = fopen( fileName, "r" ); 

    if( file == NULL )
        wprintf( L"Problem opening file.\n" );
    else
    {
        wchar_t ch;

        while( ( ch = fgetwc( file ) ) != EOF ) 
        {
            if(ch == '\n')
                words++;
        }

        fclose( file );
    }

    return words;
}


void BuildKeyboard( ElementType Keyboard[] )
{
    int i;
    ElementType E;

    wchar_t letters[NUM_DIGITS][10] = 
    {
        L"abcáàâãç",
        L"deféê",
        L"ghií",
        L"jkl",
        L"mnoóôõ",
        L"pqrs",
        L"tuvú",
        L"wxyz"
    };

    for( i = 0; i < NUM_DIGITS; i++ )
    {
        E = malloc( sizeof(ElementType) );
        E->digits = i + 2;
        E->letters = malloc( sizeof(letters[i]) );
        wcscpy( E->letters, letters[i] );
        Keyboard[i] = E;
    }
}


long int Encode( wchar_t word[], ElementType Keyboard[] )
{
    int i, j;
    wchar_t tmp[BUFFER_SIZE];
    wchar_t encoded[BUFFER_SIZE];

    i = 0;
    
    while( word[i] != L'\0' )
    {
        for( j = 0; j < NUM_DIGITS; j++ )
        {
            if( wcschr( Keyboard[j]->letters, word[i] ) != NULL)
            {
                swprintf( tmp, sizeof( tmp ), L"%ld", Keyboard[j]->digits );
                //wprintf( L"%ls\n", tmp );
                if( i == 0 )
                {
                    wcscpy( encoded, tmp );
                    //wprintf( L"%ls\n", word );
                }
                else
                {
                    wcscat( encoded, tmp );
                    //wprintf( L"%ls\n", word );
                }
                break;
            }     
        }

        i++;
    }

    return wcstol( encoded, NULL, 10 );
}


void LoadDictionary( char const* const fileName, HashTable H, ElementType Keyboard[] )
{
    FILE* file = fopen( fileName, "r" ); 

    if( file == NULL )
        wprintf( L"Problem opening file.\n" );
    else
    {
        int i;
        wchar_t line[BUFFER_SIZE];

        while( fgetws( line, sizeof( line ), file ) ) 
        {
            i = 0;

            if( !isspace(line[i]) )
            {   
                ElementType Key;
                Key = malloc( sizeof ( ElementType ) );
                //wprintf( L"CodigoAgain %ls\n", word);
                Key->digits = Encode( line, Keyboard );
                //wprintf( L"Codigo: %ld\n", Key->digits );
                line[wcslen( line ) - 1] = L'\0';
                Key->letters = malloc( ( wcslen( line ) + 1 ) * sizeof ( wchar_t ) );
                wcscpy( Key->letters, line );
                //wprintf( L"Tamanho: %ld\n", wcslen( Key->letters ) );
                //wprintf( L"Palavra: %ls\n", Key->letters );

                Insert( Key, H );
            }
        }

        fclose( file );
    }
}
