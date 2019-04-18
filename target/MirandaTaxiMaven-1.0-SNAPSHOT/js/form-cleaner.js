/**
 * Simples Biblioteca para limpar todos os campos do formulário
 * */
jQuery.fn.clear = function()
{
    var $form = $(this);

    $form.find('input:text, input:password, input:file, textarea').val('');
    $form.find('select option:selected').removeAttr('selected');
    $form.find('input:checkbox, input:radio').removeAttr('checked');

    return this;
}; 

