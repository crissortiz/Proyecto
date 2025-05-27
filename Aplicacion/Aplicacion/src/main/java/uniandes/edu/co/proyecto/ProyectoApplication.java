package uniandes.edu.co.proyecto;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import uniandes.edu.co.proyecto.modelo.Ips;
import uniandes.edu.co.proyecto.repositorio.IpsRepository;

@SpringBootApplication
public class ProyectoApplication implements CommandLineRunner{

	@Autowired
	private IpsRepository ipsRepository;
	public static void main(String[] args) {
		SpringApplication.run(ProyectoApplication.class, args);
	}

	@Override
	public void run(String... args) {

		Collection<Ips> ips = ipsRepository.findAll();
		for(Ips i: ips)
		{
			System.out.println(i);
		}
	}

}
