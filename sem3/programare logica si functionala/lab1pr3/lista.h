//
// Created by User on 10/10/2022.
//

#ifndef LAB1PR3_LISTA_H
#define LAB1PR3_LISTA_H


//tip de data generic (pentru moment este intreg)
typedef int TElem;

//referire a structurii Nod;
struct Nod;

//se defineste tipul PNod ca fiind adresa unui Nod
typedef Nod *PNod;

typedef struct Nod{
    TElem e;
    PNod urm;
};

typedef struct{
//prim este adresa primului Nod din lista
    PNod _prim;
} Lista;

//operatii pe lista - INTERFATA

//crearea unei liste din valori citite pana la 0
Lista creare();

//tiparirea elementelor unei liste
void tipar(Lista l);

// destructorul listei
void distruge(Lista l);


bool checklist(Lista list);
#endif //LAB1PR3_LISTA_H
