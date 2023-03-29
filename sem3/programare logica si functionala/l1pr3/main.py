# 3. a. Check if a list is a set.
# b. Determine the number of distinct elements from a list

class Nod:
    def __init__(self, e):
        self.e = e
        self.urm = None


class Lista:
    def __init__(self):
        self.prim = None


'''
crearea unei liste din valori citite pana la 0
'''


def creareLista():
    lista = Lista()
    lista.prim = creareLista_rec()
    return lista


def creareLista_rec():
    x = int(input("x="))
    if x == 0:
        return None
    else:
        nod = Nod(x)
        nod.urm = creareLista_rec()
        return nod


'''
tiparirea elementelor unei liste
'''


def tipar(lista):
    tipar_rec(lista.prim)


def tipar_rec(nod):
    if nod != None:
        print(nod.e)
        tipar_rec(nod.urm)


'''
program pentru test
'''


def nr_of_occurrences(e, lst):
    # 										{ 0, lst = []
    # nr_of_occurrences(e, l1, l2, ... ln) ={ 1 + nr_of_occurrences(e, l2, ... ln), e = l1
    # 										{ nr_of_occurrences(e, l2, ... ln), otherwise
    if not lst:
        return 0
    if lst.e == e:
        return 1 + nr_of_occurrences(e, lst.urm)
    else:
        return nr_of_occurrences(e, lst.urm)


# a.
def check_if_list_is_set(lst):
    # 								{ true, lst = []
    # checkIfSet(l1, l2, ... ln) =  { false, nr_of_occurrences(l1, l1, ... ln) != 1
    # 								{ check_if_list_is_set(l2, ... ln), otherwise
    if not lst:
        return True
    elif nr_of_occurrences(lst.e, lst) != 1:
        return False
    else:
        return check_if_list_is_set(lst.urm)


# b.
def distinct_elements(nr, lst, entire_list):
    # 							  { nr, lst = []
    # distinctElements(nr, lst) = { 1 + nr, nr_of_occurrences(l1, l1, ... ln) = 1
    # 							  { distinct_elements(nr, l2, ... ln)
    if not lst:
        return nr
    if nr_of_occurrences(lst.e, entire_list.prim) == 1:
        nr += 1
    return distinct_elements(nr, lst.urm, entire_list)


def main():
    lst = creareLista()
    tipar(lst)
    is_set = check_if_list_is_set(lst.prim)
    if is_set:
        print("the list is a set")
    else:
        print("the list is not a set")

    nr_elements_distinct = distinct_elements(0, lst.prim, lst)
    print("there are {0} distinct elements in the list".format(nr_elements_distinct))


main()
