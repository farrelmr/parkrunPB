package com.glenware.parkrunpb.form;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

/*
 * 
 * CREATE TABLE `prcourse` (
 *   `prcourse_id` int(11) NOT NULL AUTO_INCREMENT,
 *   `prregion_id` int(11),
 *   `prname` varchar(256) DEFAULT NULL,
 *   `url` varchar(256) DEFAULT NULL,
 *   `averagetime` int(11) DEFAULT NULL,
 *   `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 *   PRIMARY KEY (`prcourse_id`),
 *   INDEX par_ind (`prregion_id`),
 *     FOREIGN KEY (`prregion_id`)  *  
 *         REFERENCES prregion(`prregion_id`)
 *         ON DELETE CASCADE
 * ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
 * 
 * INSERT INTO prcourse(prregion_id, prname, url, averagetime) VALUES (11, 'Inverness', 'http://www.parkrun.org.uk/inverness/', 1582);
 * INSERT INTO prcourse(prregion_id, prname, url, averagetime) VALUES (11, 'Aberdeen',	'http://www.parkrun.org.uk/aberdeen/', 1586);
 * INSERT INTO prcourse(prregion_id, prname, url, averagetime) VALUES (11, 'Dundee(Camperdown)', 'http://www.parkrun.org.uk/camperdown/', 1752);
 * INSERT INTO prcourse(prregion_id, prname, url, averagetime) VALUES (11, 'St Andrews', 'http://www.parkrun.org.uk/standrews/', 1669);
 * INSERT INTO prcourse(prregion_id, prname, url, averagetime) VALUES (11, 'Perth', 'http://www.parkrun.org.uk/perth/', 1620);
 * INSERT INTO prcourse(prregion_id, prname, url, averagetime) VALUES (11, 'Edinburgh', 'http://www.parkrun.org.uk/edinburgh/', 1523);
 * INSERT INTO prcourse(prregion_id, prname, url, averagetime) VALUES (11, 'Falkirk', 'http://www.parkrun.org.uk/falkirk/', 1612);
 * INSERT INTO prcourse(prregion_id, prname, url, averagetime) VALUES (11, 'Tollcross', 'http://www.parkrun.org.uk/tollcross/', 1623);
 * INSERT INTO prcourse(prregion_id, prname, url, averagetime) VALUES (11, 'Strathclyde', 'http://www.parkrun.org.uk/strathclyde/', 1586);
 * INSERT INTO prcourse(prregion_id, prname, url, averagetime) VALUES (11, 'Victoria',	'http://www.parkrun.org.uk/victoria/', 1526);
 * INSERT INTO prcourse(prregion_id, prname, url, averagetime) VALUES (11, 'Glasgow', 'http://www.parkrun.org.uk/glasgow/', 1585);
 * INSERT INTO prcourse(prregion_id, prname, url, averagetime) VALUES (11, 'Eglington', 'http://www.parkrun.org.uk/eglinton/', 1681);
 *  
 */

@Entity
@Table(name = "PRCOURSE")
public class PRCourse {
	
	@Id
	@Column(name = "PRCOURSE_ID")
	@GeneratedValue
	private Integer id;

	@Column(name = "PRNAME")
	@NotEmpty
	private String prName;

	@Column(name = "URL")
	@NotEmpty
	private String url;

	@Column(name = "AVERAGETIME")
	@Range(min = 1)
	private int averageTime;
	
	@ManyToOne
    @JoinColumn(name="PRREGION_ID")
    private PRRegion prregion;
	
	@Transient
	private PRPredict prPredict;
	
	@Transient
	private String averageTimeString;
	
	@Transient
	private String estimatedAverageTime;
	
	@Transient
	private int regionId;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrName() {
		return prName;
	}

	public void setPrName(String prName) {
		this.prName = prName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getAverageTime() {
		return averageTime;
	}

	public void setAverageTime(int averageTime) {
		this.averageTime = averageTime;
	}
	
	public PRPredict getPrPredict() {
		return prPredict;
	}

	public void setPrPredict(PRPredict prPredict) {
		this.prPredict = prPredict;
    	if ( prPredict != null  && (prPredict.getMmInt() != 0 || prPredict.getSsInt() != 0 ) ) {
			int myAverageTime = ( getAverageTime() * prPredict.getRaceTime() ) / prPredict.getPrCourse().getAverageTime();
			int myAverageTimeMM = myAverageTime / 60;
			int myAverageTimeSS = myAverageTime % 60;
     		setEstimatedAverageTime( "" + myAverageTimeMM + ":" + ( myAverageTimeSS < 10 ? "0" + myAverageTimeSS : myAverageTimeSS ) );
    	} else {
    		setEstimatedAverageTime( "00:00" );
    	}
	}

    public String getEstimatedAverageTime() {
    	return estimatedAverageTime;
    }
	
	public void setEstimatedAverageTime(String estimatedAverageTime) {
		this.estimatedAverageTime = estimatedAverageTime;
	}

	public String getAverageTimeString() {
		return "" + averageTime / 60 + ":" + averageTime % 60;
	}

	public void setAverageTimeString(String averageTimeString) {
		this.averageTimeString = averageTimeString;
	}

	public PRRegion getPrregion() {
		return prregion;
	}

	public void setPrregion(PRRegion prregion) {
		this.prregion = prregion;
	}

	public int getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
    
}