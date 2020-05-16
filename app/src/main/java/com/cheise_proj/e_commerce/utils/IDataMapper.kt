package com.cheise_proj.e_commerce.utils

interface IDataMapper<T, M> {
    fun tToModel(t: T): M
    fun modelToT(m: M): T
}

interface IDataListMapper<T, M> : IDataMapper<T, M> {
    fun tListToModel(tList: List<T>): List<M>
    fun mListToT(mList: List<M>): List<T>
}