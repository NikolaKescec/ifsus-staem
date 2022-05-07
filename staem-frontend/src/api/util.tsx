export function createQuery(root: any): URLSearchParams {
  function helper(params: URLSearchParams, prefix: string, obj: any) {
    if (typeof obj === "undefined" || obj === null) {
      return;
    }

    if (Array.isArray(obj)) {
      for (let i = 0; i < obj.length; i++) {
        helper(params, `${prefix}[${i}]`, obj[i]);
      }
    } else if (typeof obj === "object") {
      for (const key in obj) {
        // eslint-disable-next-line no-prototype-builtins
        if (obj.hasOwnProperty(key)) {
          helper(params, prefix === "" ? key : `${prefix}.${key}`, obj[key]);
        }
      }
    } else {
      params.append(prefix, obj);
    }
  }

  const params = new URLSearchParams();
  helper(params, "", root);
  return params;
}

export function getBearerToken(): HeadersInit {
  const token = localStorage.getItem("access_token");

  if (token) {
    return {
      Authorization: `Bearer ${token}`,
    };
  } else {
    return {};
  }
}

export function transformErrors(errors: Record<string, string[]>) {
  const result = {};

  for (const key in errors) {
    const keys = key.split(".");

    let current = result;
    for (let i = 0; i < keys.length; i++) {
      if (i === keys.length - 1) {
        // @ts-ignore
        current[keys[i]] = errors[key][0];
      } else {
        // @ts-ignore
        current[keys[i]] = current[keys[i]] || {};
        // @ts-ignore
        current = current[keys[i]];
      }
    }
  }

  return result;
}
