import { TestBed } from '@angular/core/testing';
import { ApiService } from './api.service';
import { environment } from '../../environments/environment';

describe('ApiService', () => {
  let service: ApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ApiService],
    });
    service = TestBed.inject(ApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should get setores', async () => {
    const setores = await service.getSetores();
    expect(Array.isArray(setores)).toBe(true);
  });

  it('should get pasta', async () => {
    const pasta = await service.getPasta(1, 1);
    expect(pasta).toBeDefined();
  });

  it('should get pastas', async () => {
    const pastas = await service.getPastas(1);
    expect(Array.isArray(pastas)).toBe(true);
  });

  it('should get documentos', async () => {
    const documentos = await service.getDocumentos(1, 1, 'test');
    expect(Array.isArray(documentos)).toBe(true);
  });

  it('should salvar documento', async () => {
    const documento = { id: 1 };
    const result = await service.salvarDocumento(1, 1, documento);
    expect(result).toBeDefined();
  });
});

