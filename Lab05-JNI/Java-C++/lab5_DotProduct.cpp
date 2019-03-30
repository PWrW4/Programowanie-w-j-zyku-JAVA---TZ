
#include "lab5_DotProduct.h"
#include <iostream>

using namespace std;

jobject Java_lab5_DotProduct_multi01(JNIEnv *env, jobject thisObj, jobjectArray arrayA, jobjectArray arrayB) {
    jdouble sum = 0;
    int lengthA = env->GetArrayLength(arrayA);
    int lengthB = env->GetArrayLength(arrayB);
    jobject arrAObj = env->GetObjectArrayElement(arrayA, false);
    jobject arrBObj = env->GetObjectArrayElement(arrayB, false);

    jclass cls = env->GetObjectClass(arrAObj);
    jmethodID doubleValue = env->GetMethodID(cls, "doubleValue", "()D");

    if (doubleValue == NULL)
        return NULL;

    double *arrA = new double[lengthA];
    double *arrB = new double[lengthB];
    double sumD = 0.0;

    for (int i = 0; i < lengthA; ++i) {

        arrAObj = env->GetObjectArrayElement(arrayA, i);
        arrA[i] = env->CallDoubleMethod(arrAObj, doubleValue);

        arrBObj = env->GetObjectArrayElement(arrayB, i);
        arrB[i] = env->CallDoubleMethod(arrBObj, doubleValue);

        sumD += arrA[i] * arrB[i];
//        cout<<arrA[i] * arrB[i]<<endl;
    }

    sum = static_cast<jdouble>(sumD);

    jmethodID init = env->GetMethodID(cls, "<init>", "(D)V");
    jobject returnDouble = env->NewObject(cls, init, sum);

    delete arrA, arrB;
    env->DeleteLocalRef(cls);
    env->DeleteLocalRef(arrAObj);
    env->DeleteLocalRef(arrBObj);

    return returnDouble;
}

jobject Java_lab5_DotProduct_multi02(JNIEnv *env, jobject thisObj, jobjectArray arrayA) {

    jclass clsA;
    jfieldID fid;
    jobjectArray arrayB;

    clsA = env->GetObjectClass(thisObj);
    fid = env->GetFieldID(clsA, "b", "[Ljava/lang/Double;");
    if (fid == NULL)
        return NULL;

    arrayB = (jobjectArray)env->GetObjectField(thisObj, fid);

    jdouble sum = 0;
    int lengthA = env->GetArrayLength(arrayA);
    int lengthB = env->GetArrayLength(arrayB);
    jobject arrAObj = env->GetObjectArrayElement(arrayA, false);
    jobject arrBObj = env->GetObjectArrayElement(arrayB, false);

    jclass cls = env->GetObjectClass(arrAObj);
    jmethodID doubleValue = env->GetMethodID(cls, "doubleValue", "()D");

    if (doubleValue == NULL)
        return NULL;

    double *arrA = new double[lengthA];
    double *arrB = new double[lengthB];
    double sumD = 0.0;

    for (int i = 0; i < lengthA; ++i) {

        arrAObj = env->GetObjectArrayElement(arrayA, i);
        arrA[i] = env->CallDoubleMethod(arrAObj, doubleValue);

        arrBObj = env->GetObjectArrayElement(arrayB, i);
        arrB[i] = env->CallDoubleMethod(arrBObj, doubleValue);

        sumD += arrA[i] * arrB[i];
//        cout<<arrA[i] * arrB[i]<<endl;
    }

    sum = static_cast<jdouble>(sumD);

    jmethodID init = env->GetMethodID(cls, "<init>", "(D)V");
    jobject returnDouble = env->NewObject(cls, init, sum);

    delete arrA, arrB;
    env->DeleteLocalRef(cls);
    env->DeleteLocalRef(clsA);
    env->DeleteLocalRef(arrAObj);
    env->DeleteLocalRef(arrBObj);

    return returnDouble;
}

void Java_lab5_DotProduct_multi03(JNIEnv *env, jobject thisObj) {
//    jobjectArray a[] = {1.0,3.0,-5.0};
//    jobjectArray b[] = {4.0,-2.0,-1.0};
//
//    jclass cls = env->GetObjectClass(thisObj);
//    jfieldID fid = env->GetFieldID(cls, "a", "[D");
//
//    jsize len = env->GetArrayLength(a);
//
//    env->SetDoubleArrayRegion(a,0,len,)

}

