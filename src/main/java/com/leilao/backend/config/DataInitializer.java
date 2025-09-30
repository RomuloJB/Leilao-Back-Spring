package com.leilao.backend.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.leilao.backend.model.Perfil;
import com.leilao.backend.model.Pessoa;
import com.leilao.backend.model.PessoaPerfil;
import com.leilao.backend.repository.PerfilRepository;
import com.leilao.backend.repository.PessoaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final PerfilRepository perfilRepository;
    private final PessoaRepository pessoaRepository;
    private final PasswordEncoder passwordEncoder;

    private static final String PERFIL_PADRAO = "ROLE_ADMIN";
    private static final String ADMIN_EMAIL = "admin@admin.com";
    private static final String ADMIN_SENHA = "adm1234";
    private static final String ADMIN_NOME = "Administrador do Sistema";

    @Override
    @Transactional
    public void run(String... args) {
        inicializarPerfilEPessoa();
    }

    private void inicializarPerfilEPessoa() {
        Perfil perfil = perfilRepository.findByNome(PERFIL_PADRAO)
                .orElseGet(() -> {
                    Perfil p = new Perfil();
                    p.setNome(PERFIL_PADRAO);
                    Perfil salvo = perfilRepository.save(p);
                    log.info("Perfil padrão criado: {}", salvo.getNome());
                    return salvo;
                });

        if (pessoaRepository.findByEmail(ADMIN_EMAIL).isEmpty()) {
            Pessoa admin = new Pessoa();
            admin.setNome(ADMIN_NOME);
            admin.setEmail(ADMIN_EMAIL);
            admin.setSenha(passwordEncoder.encode(ADMIN_SENHA));

            PessoaPerfil pessoaPerfil = new PessoaPerfil();
            pessoaPerfil.setPerfil(perfil);
            pessoaPerfil.setPessoa(admin);

            admin.setPessoaPerfil(List.of(pessoaPerfil));

            pessoaRepository.save(admin);
            log.info("Usuário admin criado: {}", ADMIN_EMAIL);
        } else {
            log.info("Usuário admin já existe: {}", ADMIN_EMAIL);
        }
    }
}
