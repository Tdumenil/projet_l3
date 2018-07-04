#!/usr/bin/python3.5
# -*-coding:Utf-8 -*

# comparaison entre les points sur les photos et les points tirés de la webcam
# via un systeme d'addition des longueurs caracteristiques
def comparaison (point,liste) :
    l = len(liste)
    i = 0
    b=0
    c=0
    d=0
    y=0
    a = []
    while (i<len(point)):
        b += (point[i])
        i += 1
    i = 0
    pointr = []
    while (i<l) :
        pointr = []
        pointr = liste[i]
        j = 0
        while (j<len(pointr)):
            d += (pointr[j])
            j += 1
        a.append(d)
        if (c==0) :
            c = abs(b-a[i])
            y = i
        elif (c < abs(b-a[i])):
            c = abs(b-a[i])
            y = i
        i += 1
    return y                   