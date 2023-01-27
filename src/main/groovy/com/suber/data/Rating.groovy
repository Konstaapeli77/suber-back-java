package com.suber.data

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table


@Entity
@Table(name = 'rating')
class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id

    @Column
    Integer stars

    @Column
    String comment

    @Column
    Integer recommend

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = 'service_id', referencedColumnName = 'id')
    private Service service

}
