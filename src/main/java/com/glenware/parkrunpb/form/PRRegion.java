package com.glenware.parkrunpb.form;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;;

/** 
 * CREATE TABLE `prregion` (
 *    `prregion_id` int(11) NOT NULL AUTO_INCREMENT,
 *    `regionname` varchar(256) DEFAULT NULL,
 *    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 *   PRIMARY KEY (`prregion_id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
 * 
 * select * from prregion order by regionname
 * 
 * INSERT INTO PRREGION(REGIONNAME) VALUES ('East Midlands');
 * INSERT INTO PRREGION(REGIONNAME) VALUES ('East of England');
 * INSERT INTO PRREGION(REGIONNAME) VALUES ('Greater London');
 * INSERT INTO PRREGION(REGIONNAME) VALUES ('North East England');
 * INSERT INTO PRREGION(REGIONNAME) VALUES ('North West England');
 * INSERT INTO PRREGION(REGIONNAME) VALUES ('Northern Ireland');
 * INSERT INTO PRREGION(REGIONNAME) VALUES ('Overseas Military Bases');
 * INSERT INTO PRREGION(REGIONNAME) VALUES ('Scotland');
 * INSERT INTO PRREGION(REGIONNAME) VALUES ('South East England');
 * INSERT INTO PRREGION(REGIONNAME) VALUES ('South West England');
 * INSERT INTO PRREGION(REGIONNAME) VALUES ('Wales');
 * INSERT INTO PRREGION(REGIONNAME) VALUES ('West Midlands');
 * INSERT INTO PRREGION(REGIONNAME) VALUES ('Yorkshire and Humberside');
 */

@Entity
@Table(name = "PRREGION")
public class PRRegion {

	@Id
	@Column(name = "prregion_id")
	@GeneratedValue
	private Integer id;

	@Column(name = "regionname")
	private String regionName;
	
    @OneToMany(mappedBy="prregion")
    private Set<PRCourse> prCourses;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setPrName(String regionName) {
		this.regionName = regionName;
	}
	
}