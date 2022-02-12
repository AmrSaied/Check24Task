package com.check24.base.baseEntities

/**
 * base data item class that is used in [BasePagedListAdapter] for the Diff callback id comparison
 */
interface  BaseEntity {

    fun entityId():Int
}