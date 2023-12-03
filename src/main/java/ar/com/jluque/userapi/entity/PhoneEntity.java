package ar.com.jluque.userapi.entity;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_PHONE")
public class PhoneEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PHO_ID", columnDefinition = "BINARY(16)")
	private UUID id;

	@Column(name = "PHO_NUMBER", unique = true, nullable = false)
	private String number;

	@Column(name = "PHO_CITY", unique = false, nullable = false)
	private String cityCode;

	@Column(name = "PHO_COUNTRY", unique = false, nullable = false)
	private String countryCode;

	@ManyToOne
	@JoinColumn(name = "USR_ID")
	@JsonIgnore
	private UserEntity user;
}
