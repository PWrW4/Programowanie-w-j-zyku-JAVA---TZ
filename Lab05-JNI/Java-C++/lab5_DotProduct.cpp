
#include "lab5_DotProduct.h"
#include <iostream>

using namespace std;

jdouble Java_lab5_DotProduct_multi01(JNIEnv * env, jobject thisObj, jdoubleArray arrayA, jdoubleArray arrayB) {
    jdouble sum = 0;

    jsize len = env->GetArrayLength(arrayA);

    jdouble *arrA = env->GetDoubleArrayElements(arrayA,false);
    jdouble *arrB = env->GetDoubleArrayElements(arrayB,false);

    for (int i = 0; i < len; ++i) {
        sum += arrA[i] * arrB[i];
//        cout<<arrA[i] * arrB[i]<<endl;
    }

    env->ReleaseDoubleArrayElements(arrayA, arrA, 0);
    env->ReleaseDoubleArrayElements(arrayB, arrB, 0);

    return sum;
}

jdouble Java_lab5_DotProduct_multi02(JNIEnv * env, jobject thisObj, jdoubleArray arrayA) {
    jdouble sum = 0;

    jclass cls = env->GetObjectClass(thisObj);
    jfieldID fid = env->GetFieldID(cls, "b", "[D");

    if (fid == 0)
        return -1;

    jobject arrayBObj= env->GetObjectField(thisObj,fid);

    jdoubleArray * arrayB = reinterpret_cast<jdoubleArray*>(&arrayBObj);

    jsize len = env->GetArrayLength(arrayA);

    jdouble *arrA = env->GetDoubleArrayElements(arrayA,false);
    jdouble *arrB = env->GetDoubleArrayElements(*arrayB,false);



    for (int i = 0; i < len; ++i) {
        sum += arrA[i] * arrB[i];
//        cout<<arrA[i] * arrB[i]<<endl;
    }

    env->ReleaseDoubleArrayElements(arrayA, arrA, 0);
    env->ReleaseDoubleArrayElements(*arrayB, arrB, 0);

    return sum;
}

void Java_lab5_DotProduct_multi03(JNIEnv * env, jobject thisObj) {
    jdoubleArray a[] = {1.0,3.0,-5.0};
    jdoubleArray b[] = {4.0,-2.0,-1.0};

    jclass cls = env->GetObjectClass(thisObj);
    jfieldID fid = env->GetFieldID(cls, "a", "[D");

    jsize len = env->GetArrayLength(a);

    env->SetDoubleArrayRegion(a,0,len,)

}

