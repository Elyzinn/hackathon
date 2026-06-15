async function buscarCep(cep) {
    cep = cep.replace(/\D/g, ''); // remove não-dígitos

    const loading = document.getElementById('cep-loading');
    const erro = document.getElementById('cep-erro');

    // Limpa estados anteriores
    loading.style.display = 'none';
    erro.style.display = 'none';

    if (cep.length !== 8) return;

    loading.style.display = 'inline';

    try {
        const res = await fetch(`https://viacep.com.br/ws/${cep}/json/`);
        const data = await res.json();

        loading.style.display = 'none';

        if (data.erro) {
            erro.style.display = 'inline';
            return;
        }

        // Preenche os campos automaticamente
        document.getElementById('logradouro').value = data.logradouro || '';
        document.getElementById('bairro').value = data.bairro || '';
        document.getElementById('cidade').value = data.localidade || '';
        document.getElementById('estado').value = data.uf || '';

        // Foca no campo número após preenchimento
        document.getElementById('numero')?.focus();

    } catch (e) {
        loading.style.display = 'none';
        erro.style.display = 'inline';
    }
}

function executarRecuperacao(event) {
    event.preventDefault();
    document.getElementById('form-recuperar').style.display = 'none';
    document.getElementById('msg-sucesso').style.display = 'block';
}

function executarRecuperacao(event) {
    event.preventDefault();
    document.getElementById('form-recuperar').style.display = 'none';
    document.getElementById('msg-sucesso').style.display = 'block';
}