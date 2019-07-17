package br.com.atlas.base.entity

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
interface DomainMappable<ENTITY> {
    fun toDomain(): ENTITY
}